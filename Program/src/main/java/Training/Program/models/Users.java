package Training.Program.models;

public class Users {
		private String userName;
		private String emailId;
		private String password;
		private String confirmPassword;
		
		public Users() {
			
		}

	

		public String getUserName() {
			return userName;
		}

		public Users(String userName, String emailId, String password, String confirmPassword) {
			super();
			this.userName = userName;
			this.emailId = emailId;
			this.password = password;
			this.confirmPassword = confirmPassword;
		}



		
		public String getEmailId() {
			return emailId;
		}



		public void setEmailId(String emailId) {
			this.emailId = emailId;
		}



		public void setUserName(String userName) {
			this.userName = userName;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getConfirmPassword() {
			return confirmPassword;
		}

		public void setConfirmPassword(String confirmPassword) {
			this.confirmPassword = confirmPassword;
		}
		
		 public boolean confirmPassword() {return this.password.equals(this.confirmPassword);}
}