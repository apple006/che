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
package org.eclipse.che.plugin.testing.ide.messages;

/**
 *
 */
public class Message extends ClientTestingMessage {

    Message() {
    }

    public String getText() {
        return getAttributeValue("text");
    }

    public String getErrorDetails() {
        return getAttributeValue("errorDetails");
    }

    @Override
    public void visit(TestingMessageVisitor visitor) {
        visitor.visitMessageWithStatus(this);
    }
}