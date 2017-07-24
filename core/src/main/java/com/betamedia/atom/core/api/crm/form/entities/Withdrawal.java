package com.betamedia.atom.core.api.crm.form.entities;

public class Withdrawal {

	public final String withdrawalAmount;
	public final String withdrawalbankName;
	public final String withdrawalBeneficaryCity;
	public final String withdrawalBeneficaryName;
	public final String withdrawalBranch;
	public final String withdrawalIban;
	public final String withdrawalCountryCode;
	public final String withdrawalRoutingNumber;
	public final String withdrawalSwift;
	public final String withdrawalComment;

	private Withdrawal(String withdrawalAmount, String withdrawalbankName, String withdrawalBeneficaryCity,
			String withdrawalBeneficaryName, String withdrawalBranch, String withdrawalIban,
			String withdrawalCountryCode, String withdrawalRoutingNumber, String withdrawalSwift,
			String withdrawalComment) {
		this.withdrawalAmount = withdrawalAmount;
		this.withdrawalbankName = withdrawalbankName;
		this.withdrawalBeneficaryCity = withdrawalBeneficaryCity;
		this.withdrawalBeneficaryName = withdrawalBeneficaryName;
		this.withdrawalBranch = withdrawalBranch;
		this.withdrawalIban = withdrawalIban;
		this.withdrawalCountryCode = withdrawalCountryCode;
		this.withdrawalRoutingNumber = withdrawalRoutingNumber;
		this.withdrawalSwift = withdrawalSwift;
		this.withdrawalComment = withdrawalComment;
	}

	public static WithdrawalBuilder builder() {
		return new WithdrawalBuilder();
	}

	public static class WithdrawalBuilder {

		private String withdrawalAmount = "50";
		private String withdrawalbankName = "CityBank";
		private String withdrawalBeneficaryCity = "Australia";
		private String withdrawalBeneficaryName = "tyutuytu";
		private String withdrawalBranch = "yuiyiuyi";
		private String withdrawalIban = "yuiyiyiu";
		private String withdrawalCountryCode = "DE";
		private String withdrawalRoutingNumber = "78979879";
		private String withdrawalSwift = "UIUOUO";
		private String withdrawalComment = "YUIYUYIU";

		private WithdrawalBuilder() {}
		
		public WithdrawalBuilder withwithdrawalAmount(String withdrawalAmount) {
			this.withdrawalAmount = withdrawalAmount;
			return this;
		}

		public WithdrawalBuilder withwithdrawalbankName(String withdrawalbankName) {
			this.withdrawalbankName = withdrawalbankName;
			return this;
		}

		public WithdrawalBuilder withwithdrawalBeneficaryCity(String withdrawalBeneficaryCity) {
			this.withdrawalBeneficaryCity = withdrawalBeneficaryCity;
			return this;
		}

		public WithdrawalBuilder withwithdrawalBeneficaryName(String withdrawalBeneficaryName) {
			this.withdrawalBeneficaryName = withdrawalBeneficaryName;
			return this;
		}

		public WithdrawalBuilder withwithdrawalBranch(String withdrawalBranch) {
			this.withdrawalBranch = withdrawalBranch;
			return this;
		}

		public WithdrawalBuilder withwithdrawalIban(String withdrawalIban) {
			this.withdrawalBranch = withdrawalIban;
			return this;
		}

		public WithdrawalBuilder withwithdrawalCountryCode(String withdrawalCountryCode) {
			this.withdrawalCountryCode = withdrawalCountryCode;
			return this;
		}

		public WithdrawalBuilder withwwithdrawalRoutingNumberCode(String withdrawalRoutingNumber) {
			this.withdrawalRoutingNumber = withdrawalRoutingNumber;
			return this;
		}

		public WithdrawalBuilder withwithdrawalSwift(String withdrawalSwift) {
			this.withdrawalSwift = withdrawalSwift;
			return this;
		}

		public WithdrawalBuilder withwithdrawalComment(String withdrawalComment) {
			this.withdrawalComment = withdrawalComment;
			return this;
		}
	

	public Withdrawal build() {
		return new Withdrawal(
				withdrawalAmount, 
				withdrawalbankName, 
				withdrawalBeneficaryCity,
				withdrawalBeneficaryName, 
				withdrawalBranch, 
				withdrawalIban, 
				withdrawalCountryCode,
				withdrawalRoutingNumber, 
				withdrawalSwift, 
				withdrawalComment
				);
	
		}
	}
}
