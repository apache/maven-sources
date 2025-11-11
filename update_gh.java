///usr/bin/env jbang "$0" "$@" ; exit $?

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;

public class update_gh {
    private static class Project {
        final String org;
        final String group;
        final String name;
        final String repo;
        final String branch;
        Project(String org, String group, String name, String repo, String branch) {
            this.org = org;
            this.group = group;
            this.name = name;
            this.repo = repo;
            this.branch = branch;
        }
    }

    public static void main(String... args) throws Exception {
        String group = "";
        for(Project p: Files.readAllLines(Path.of("default.xml")).stream()
            .filter(s -> s.contains("project path='"))
            .map(s -> toProject(s))
            .collect(Collectors.toList())) {
                if (!group.equals(p.group)) {
                    System.out.println();
                    System.out.println("# " + p.group);
                    group = p.group;
                }
                System.out.println("- [![GitHub branch status](https://img.shields.io/github/checks-status/" + p.org + "/" + p.repo + "/" + p.branch + ")]"
                    + "(https://github.com/" + p.org + "/" + p.repo + "/tree/" + p.branch + ") "
                    + p.name + ("master".equals(p.branch) ? "" : (" " + p.branch))); 
            }
    }

    private static Project toProject(String line) {
        String path = extract(line, "path");
        String name = extract(line, "name");
        String revision = extract(line, "revision");

        int pos = (path.startsWith("core") || path.startsWith("plugins")) ? path.lastIndexOf('/') : path.indexOf('/');
        String group = "";
        if (pos > 0) {
            group = path.substring(0, pos);
            path = path.substring(pos + 1);
        }

        name = name.substring(0, name.length() - 4);

        String org = "apache";
        if (group.startsWith("plexus")) {
            org = "codehaus-plexus";
        } else if (group.equals("sisu")) {
            org = "eclipse-sisu";
        }

        return new Project(org, group, path, name, (revision == null) ? "master" : revision);
    }

    private static String extract(String line, String key) {
        int pos = line.indexOf(key);
        if (pos < 0) {
            return null;
        }
        char sep = line.charAt(pos + key.length() + 1);
        line = line.substring(pos + key.length() + 2);
        return line.substring(0, line.indexOf(sep));
    }
}