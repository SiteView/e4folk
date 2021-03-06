/*******************************************************************************
 * Copyright (c) 2011 Kai Toedter and others.
 * 
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html.
 * 
 * Contributors:
 *     Kai Toedter - initial API and implementation
 ******************************************************************************/

package com.toedter.e4.ui.workbench.addons.minmax;

import org.eclipse.e4.ui.model.application.ui.MUIElement;

@SuppressWarnings("restriction")
public interface IMinMaxAddon {
	void setGenericMinMaxAddon(GenericMinMaxAddon genericMinMaxAddon);

	void setMaximizeHandler(MUIElement element, Runnable maximizeHandler);

	void setMinimizeHandler(MUIElement element, Runnable minimizeHandler);

	void setRestoreHandler(MUIElement element, Runnable restoreHandler);
}
