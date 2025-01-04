
	import java.util.Date;

	public class payment {

	    private static final String N = null;
		private static int paymentID=0;
	    private String paymentMethod;
	    private double paymentAmount;
	    private Date paymentDate;
	    private String issuingBank;
	    public payment(String paymentMethod, double paymentAmount, String issuingBank) {
	        this.paymentMethod = paymentMethod;
	        this.paymentAmount = paymentAmount;
	        this.paymentDate = new Date(); // Set payment date to current date
	        this.issuingBank = issuingBank;
			paymentID++;
	    }
	    public void setPaymentMethod(String paymentMethod) {
	        this.paymentMethod = paymentMethod;
	    }

	    public void setPaymentAmount(double paymentAmount) {
	        this.paymentAmount = paymentAmount;
	    }

	    public void setIssuingBank(String issuingBank) {
	        this.issuingBank = issuingBank;
	    }


	    public String getPaymentMethod() {
	        return paymentMethod;
	    }

	    public double getPaymentAmount() {
	        return paymentAmount;
	    }

	    public String getIssuingBank() {
	        return issuingBank;
	    }


	    public Date getPaymentDate() {
	        return paymentDate;
	    }
	    @Override
	    public String toString() {
	        return "paymentID=" + paymentID + "\n"+"paymentMethod=" + paymentMethod + "\n" + "paymentAmount=" + paymentAmount +"\n"+"paymentDate=" + paymentDate +"\n"+"issuingBank=" + issuingBank + "\n" ;
	    }
	}


