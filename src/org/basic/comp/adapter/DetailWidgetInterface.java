package org.basic.comp.adapter;

import org.basic.comp.listener.WidgetInterface;


public interface DetailWidgetInterface extends WidgetInterface{
	public void actionReset();
	public void setMasterEfectWidget(EfectWidget master) ;
	public void setTypeEfectWidget(int typeEfectWidget);
}
