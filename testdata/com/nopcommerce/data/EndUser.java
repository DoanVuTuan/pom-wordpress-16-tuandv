package com.nopcommerce.data;

import commons.DataHelper;

public class EndUser {
	DataHelper data = DataHelper.getData();
	public class Register {
		public final String FIRST_NAME = data.getFirstName();
		public final String LAST_NAME ="";
		public final String COMPANY_NAME ="";
		public final String EMAIL ="";
		public final String PASSWORD ="";
	
	}
}
