package org.apache.maven.repository.legacy;

import org.apache.maven.wagon.Wagon;
import org.codehaus.plexus.component.annotations.Component;

/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

/**
 * Wagon for testing, for protocol <code>c</code>
 *
 * @author <a href="mailto:carlos@apache.org">Carlos Sanchez</a>
 * @author <a href="mailto:jason@maven.org">Jason van Zyl</a>
 */
@Component(role=Wagon.class,hint="c")
public class WagonC
    extends WagonMock
{
    public String[] getSupportedProtocols()
    {
        return new String[]{ "c" };
    }
}
