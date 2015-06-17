/**
 * Axelor Business Solutions
 *
 * Copyright (C) 2014 Axelor (<http://axelor.com>).
 *
 * This program is free software: you can redistribute it and/or  modify
 * it under the terms of the GNU Affero General Public License, version 3,
 * as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.axelor.apps.supplychain.service;

import java.math.BigDecimal;
import java.util.List;

import com.axelor.apps.account.db.Invoice;
import com.axelor.apps.account.db.InvoiceLine;
import com.axelor.apps.account.service.invoice.generator.InvoiceGenerator;
import com.axelor.apps.sale.db.SaleOrder;
import com.axelor.apps.sale.db.SaleOrderLine;
import com.axelor.exception.AxelorException;
import com.google.inject.persist.Transactional;

public interface SaleOrderInvoiceService {

	public Invoice generateInvoice(SaleOrder saleOrder) throws AxelorException;


	@Transactional(rollbackOn = {AxelorException.class, Exception.class})
	public Invoice generatePerOrderInvoice(SaleOrder saleOrder) throws AxelorException;


	@Transactional(rollbackOn = {AxelorException.class, Exception.class})
	public Invoice generateSubscriptionInvoice(SaleOrder saleOrder) throws AxelorException;


	public void checkSubscriptionSaleOrder(SaleOrder saleOrder) throws AxelorException;




	/**
	 * Cree une facture mémoire à partir d'un devis.
	 *
	 * Le planificateur doit être prêt.
	 *
	 * @param saleOrder
	 * 		Le devis
	 *
	 * @return Invoice
	 * 		La facture d'abonnement
	 *
	 * @throws AxelorException
	 * @throws Exception
	 */
	public Invoice runSubscriptionInvoicing(SaleOrder saleOrder) throws AxelorException;

	public boolean checkIfSaleOrderIsCompletelyInvoiced(SaleOrder saleOrder);

	public SaleOrder fillSaleOrder(SaleOrder saleOrder, Invoice invoice);


	public SaleOrder assignInvoice(SaleOrder saleOrder, Invoice invoice);

	public Invoice createInvoice(SaleOrder saleOrder) throws AxelorException;

	public Invoice createInvoice(SaleOrder saleOrder, List<SaleOrderLine> saleOrderLineList) throws AxelorException;


	public InvoiceGenerator createInvoiceGenerator(SaleOrder saleOrder) throws AxelorException;



	// TODO ajouter tri sur les séquences
	public List<InvoiceLine> createInvoiceLines(Invoice invoice, List<SaleOrderLine> saleOrderLineList) throws AxelorException;

	public List<InvoiceLine> createInvoiceLine(Invoice invoice, SaleOrderLine saleOrderLine) throws AxelorException;

	public BigDecimal getAmountInvoiced(SaleOrder saleOrder);

	public BigDecimal getAmountInvoiced(SaleOrder saleOrder, Long exceptInvoiceId, boolean includeInvoice);

}


