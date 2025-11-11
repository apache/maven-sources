///usr/bin/env jbang "$0" "$@" ; exit $?

//DEPS com.fasterxml.jackson.core:jackson-databind:2.20.1

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

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
        Set<String> gitRepos = fetchApacheGitRepos();
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
            gitRepos.remove(p.repo);
        }
        if (gitRepos.size() > 0) {
            System.out.println();
            System.out.println("# not listed in default.xml");
            for(String name: gitRepos) {
                System.out.println("- [" + name + "](https://github.com/apache/" + name + ")");
            }
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

    private static Set<String> fetchApacheGitRepos() throws IOException, InterruptedException {
        String jsonContent = HttpClient.newHttpClient()
                .send(HttpRequest.newBuilder(URI.create("https://gitbox.apache.org/repositories.json")).build(),
                      HttpResponse.BodyHandlers.ofString())
                .body();
        
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(jsonContent);

        Set<String> git = new HashSet<>();
        for (Map.Entry<String, JsonNode> e: rootNode.findValue("projects").findValue("maven").findValue("repositories").properties()) {
            if (!e.getValue().findValue("description").asText().contains("(archived)")) {
                git.add(e.getKey());
            }
        }

        return git;
    }
}