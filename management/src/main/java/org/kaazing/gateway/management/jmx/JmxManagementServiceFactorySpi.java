/**
 * Copyright 2007-2015, Kaazing Corporation. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kaazing.gateway.management.jmx;

import java.util.Collection;
import java.util.Collections;
import org.kaazing.gateway.service.Service;
import org.kaazing.gateway.service.ServiceFactorySpi;

public class JmxManagementServiceFactorySpi extends ServiceFactorySpi {

    @Override
    public Collection<String> getServiceTypes() {
        return Collections.singletonList("management.jmx");
    }

    @Override
    public Service newService(String serviceType) {
        assert "management.jmx".equals(serviceType);
        return new JmxManagementService();
    }

}
