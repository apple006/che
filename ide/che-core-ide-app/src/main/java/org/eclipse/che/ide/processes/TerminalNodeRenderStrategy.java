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
package org.eclipse.che.ide.processes;

import elemental.dom.Element;
import elemental.dom.Node;
import elemental.events.Event;
import elemental.html.DivElement;
import elemental.html.SpanElement;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import org.eclipse.che.ide.CoreLocalizationConstant;
import org.eclipse.che.ide.api.parts.PartStackUIResources;
import org.eclipse.che.ide.machine.MachineResources;
import org.eclipse.che.ide.ui.Tooltip;
import org.eclipse.che.ide.util.dom.Elements;
import org.vectomatic.dom.svg.ui.SVGImage;
import org.vectomatic.dom.svg.ui.SVGResource;

import static org.eclipse.che.ide.ui.menu.PositionController.HorizontalAlign.MIDDLE;
import static org.eclipse.che.ide.ui.menu.PositionController.VerticalAlign.BOTTOM;
import static org.eclipse.che.ide.util.dom.DomUtils.ensureDebugId;

/**
 * Strategy for rendering a terminal node.
 *
 * @author Vlad Zhukovskyi
 * @see ProcessTreeNodeRenderStrategy
 * @see HasStopProcessHandler
 * @since 5.11.0
 */
@Singleton
public class TerminalNodeRenderStrategy implements ProcessTreeNodeRenderStrategy, HasStopProcessHandler {

    private final MachineResources         resources;
    private final PartStackUIResources     partResources;
    private final CoreLocalizationConstant locale;

    private StopProcessHandler stopProcessHandler;

    @Inject
    public TerminalNodeRenderStrategy(MachineResources resources,
                                      PartStackUIResources partResources,
                                      CoreLocalizationConstant locale) {
        this.resources = resources;
        this.partResources = partResources;
        this.locale = locale;
    }

    @Override
    public SpanElement renderSpanElementFor(ProcessTreeNode candidate) {
        return createTerminalElement(candidate);
    }

    private SpanElement createTerminalElement(ProcessTreeNode node) {
        SpanElement root = Elements.createSpanElement();
        ensureDebugId(root, "terminal-root-element");

        root.appendChild(createCloseElement(node));

        SVGResource icon = node.getTitleIcon();
        if (icon != null) {
            SpanElement iconElement = Elements.createSpanElement(resources.getCss().processIcon());
            ensureDebugId(iconElement, "terminal-icon-element");

            root.appendChild(iconElement);

            DivElement divElement = Elements.createDivElement(resources.getCss().processIconPanel());
            iconElement.appendChild(divElement);

            divElement.appendChild((Node)new SVGImage(icon).getElement());
        }

        Element nameElement = Elements.createSpanElement();
        nameElement.setTextContent(node.getName());
        ensureDebugId(nameElement, "terminal-name-element");

        Tooltip.create(nameElement, BOTTOM, MIDDLE, node.getName());
        root.appendChild(nameElement);

        Element spanElement = Elements.createSpanElement();
        spanElement.setInnerHTML("&nbsp;");
        root.appendChild(spanElement);

        return root;
    }

    private SpanElement createCloseElement(final ProcessTreeNode node) {
        SpanElement closeButton = Elements.createSpanElement(resources.getCss().processesPanelCloseButtonForProcess());
        ensureDebugId(closeButton, "close-terminal-node-button");

        SVGImage icon = new SVGImage(partResources.closeIcon());
        closeButton.appendChild((Node)icon.getElement());

        Tooltip.create(closeButton, BOTTOM, MIDDLE, locale.viewCloseProcessOutputTooltip());

        closeButton.addEventListener(Event.CLICK, event -> {
            if (stopProcessHandler != null) {
                stopProcessHandler.onCloseProcessOutputClick(node);
            }
        }, true);

        return closeButton;
    }

    @Override
    public void addStopProcessHandler(StopProcessHandler handler) {
        stopProcessHandler = handler;
    }
}
