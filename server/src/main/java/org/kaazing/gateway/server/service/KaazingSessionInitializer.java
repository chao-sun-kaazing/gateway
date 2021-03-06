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
package org.kaazing.gateway.server.service;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.session.IoSessionInitializer;
import org.kaazing.gateway.service.ServiceContext;


/**
 * The interface that defines service-session initializers.
 */
public interface KaazingSessionInitializer<T extends ConnectFuture> extends IoSessionInitializer<ConnectFuture> {

    /**
     * Initialize the session initializer.
     *
     * @param parentInitializer
     * @param address
     */
    void initialize(KaazingSessionInitializer<T> parentInitializer, ServiceContext serviceContext, int sessionType);
}
