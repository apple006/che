/*******************************************************************************
 * Copyright (c) 2012-2017 Codenvy, S.A.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Codenvy, S.A. - initial API and implementation
 *******************************************************************************/
package org.eclipse.che.api.workspace.shared.dto.event;

import org.eclipse.che.api.core.model.workspace.runtime.BootstrapperStatus;
import org.eclipse.che.api.workspace.shared.dto.RuntimeIdentityDto;
import org.eclipse.che.dto.shared.DTO;

/**
 * Bootstrapper event status DTO.
 *
 * @author Max Shaposhnik (mshaposhnik@codenvy.com)
 */
@DTO
public interface BootstrapperStatusEvent {

    BootstrapperStatus getStatus();

    void setStatus(BootstrapperStatus status);

    BootstrapperStatusEvent withStatus(BootstrapperStatus status);

    String getMachineName();

    void setMachineName(String machineName);

    BootstrapperStatusEvent withMachineName(String machineName);


    RuntimeIdentityDto getRuntimeId();

    void setRuntimeId(RuntimeIdentityDto runtimeId);

    BootstrapperStatusEvent withRuntimeId(RuntimeIdentityDto runtimeId);


    String getError();

    void setError(String error);

    BootstrapperStatusEvent withError(String error);


    String getTime();

    void setTime(String time);

    BootstrapperStatusEvent withTime(String time);
}
