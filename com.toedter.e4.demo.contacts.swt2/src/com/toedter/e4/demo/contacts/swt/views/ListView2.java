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

package com.toedter.e4.demo.contacts.swt.views;

import com.toedter.e4.demo.contacts.generic.model.Contact;
import com.toedter.e4.demo.contacts.generic.model.ContactsRepositoryFactory;
import javax.annotation.PreDestroy;
import javax.inject.Inject;
import org.eclipse.core.databinding.beans.BeansObservables;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.map.IObservableMap;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
import org.eclipse.jface.databinding.viewers.ObservableMapLabelProvider;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.layout.TableColumnLayout;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;

@SuppressWarnings("restriction")
public class ListView2 {

	private final TableViewer contactsViewer;

	@Inject
	public ListView2(Composite parent, final MApplication application) {
		// Table composite (because of TableColumnLayout)
		final Composite tableComposite = new Composite(parent, SWT.NONE);
		tableComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		final TableColumnLayout tableColumnLayout = new TableColumnLayout();
		tableComposite.setLayout(tableColumnLayout);

		// Table viewer
		contactsViewer = new TableViewer(tableComposite, SWT.FULL_SELECTION);
		contactsViewer.getTable().setHeaderVisible(true);
		contactsViewer.getTable().setLinesVisible(true);
		contactsViewer.setComparator(new ContactViewerComparator());

		contactsViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				IStructuredSelection selection = (IStructuredSelection) event.getSelection();
				// selectionService.setSelection(selection.getFirstElement());
				application.getContext().set(Contact.class, (Contact) selection.getFirstElement());
			}
		});

		// First name column
		final TableViewerColumn firstNameColumn = new TableViewerColumn(contactsViewer, SWT.NONE);
		firstNameColumn.getColumn().setText("First Name");
		tableColumnLayout.setColumnData(firstNameColumn.getColumn(), new ColumnWeightData(40));

		// Last name column
		final TableViewerColumn lastNameColumn = new TableViewerColumn(contactsViewer, SWT.NONE);
		lastNameColumn.getColumn().setText("Last Name");
		tableColumnLayout.setColumnData(lastNameColumn.getColumn(), new ColumnWeightData(60));

		ObservableListContentProvider contentProvider = new ObservableListContentProvider();

		contactsViewer.setContentProvider(contentProvider);

		IObservableMap[] attributes = BeansObservables.observeMaps(contentProvider.getKnownElements(), Contact.class,
				new String[] { "firstName", "lastName" });
		contactsViewer.setLabelProvider(new ObservableMapLabelProvider(attributes));
		IObservableList allContacts = ContactsRepositoryFactory.getContactsRepository().getAllContacts();
		contactsViewer.setInput(allContacts);

		GridLayoutFactory.fillDefaults().generateLayout(parent);

		// Hack to select Kai Toedter at startup
		for (int i = 0; i < allContacts.size(); i++) {
			Contact contact = (Contact) allContacts.get(i);

			if ("Kai".equalsIgnoreCase(contact.getFirstName()) && "Tödter".equalsIgnoreCase(contact.getLastName())) {
				contactsViewer.setSelection(new StructuredSelection(contact));
				break;
			}
		}

	}

	@PreDestroy
	void preDestroy() {
		// for (Object object :
		// ContactsRepositoryFactory.getContactsRepository().getAllContacts()) {
		// Contact contact = (Contact) object;
		// // TODO image disposal
		// Image image = contact.getImage();
		// if (image != null) {
		// image.dispose();
		// }
		// }
	}

	@Focus
	void setFocus() {
		contactsViewer.getControl().setFocus();
	}
}
