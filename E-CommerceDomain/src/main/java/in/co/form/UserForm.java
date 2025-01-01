package in.co.form;
import java.util.Date;
import javax.validation.constraints.*;
import in.co.common.BaseDTO;
import in.co.common.BaseForm;
import in.co.dto.UserDTO;

public class UserForm extends BaseForm {

    // Common attributes
    @NotEmpty(message = "Please enter the first name")
    @Size(max = 50, message = "First name must not exceed 50 characters")
    private String firstName;

    @NotEmpty(message = "Please enter the last name")
    @Size(max = 50, message = "Last name must not exceed 50 characters")
    private String lastName;

    @NotEmpty(message = "Please enter the login ID")
    @Email(message = "Login ID must be a valid email")
    private String loginId;

    @NotEmpty(message = "Please enter the password")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String password;

    @NotEmpty(message = "Please enter the role")
    @Pattern(regexp = "merchant|consumer", message = "Role must be either 'MERCHANT' or 'CONSUMER'")
    private String role;

    @NotNull(message = "Please enter the date of birth")
    @Past(message = "Date of birth must be in the past")
    private Date dob;

    // Merchant-specific attributes
    private String businessName;
    private String gstNumber;
    private String businessAddress;

    // Consumer-specific attributes
    private String deliveryAddress;
    private String contactNumber;

    // Conditional Validation for Merchant role
    @AssertTrue(message = "Business details are required for MERCHANT role")
    public boolean isMerchantValid() {
        if ("merchant".equalsIgnoreCase(role)) {
            return businessName != null && !businessName.isEmpty() &&
                   gstNumber != null && !gstNumber.isEmpty() &&
                   businessAddress != null && !businessAddress.isEmpty();
        }
        return true;  // Ignore validation if not MERCHANT
    }

    // Conditional Validation for Consumer role
    @AssertTrue(message = "Delivery address and contact number are required for CONSUMER role")
    public boolean isConsumerValid() {
        if ("consumer".equalsIgnoreCase(role)) {
            return deliveryAddress != null && !deliveryAddress.isEmpty() &&
                   contactNumber != null && !contactNumber.isEmpty();
        }
        return true;  // Ignore validation if not CONSUMER
    }

    // Getters and Setters (Same as before) ...
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getLoginId() { return loginId; }
    public void setLoginId(String loginId) { this.loginId = loginId; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public Date getDob() { return dob; }
    public void setDob(Date dob) { this.dob = dob; }

    public String getBusinessName() { return businessName; }
    public void setBusinessName(String businessName) { this.businessName = businessName; }

    public String getGstNumber() { return gstNumber; }
    public void setGstNumber(String gstNumber) { this.gstNumber = gstNumber; }

    public String getBusinessAddress() { return businessAddress; }
    public void setBusinessAddress(String businessAddress) { this.businessAddress = businessAddress; }

    public String getDeliveryAddress() { return deliveryAddress; }
    public void setDeliveryAddress(String deliveryAddress) { this.deliveryAddress = deliveryAddress; }

    public String getContactNumber() { return contactNumber; }
    public void setContactNumber(String contactNumber) { this.contactNumber = contactNumber; }

    @Override
    public BaseDTO getDTO() {
        UserDTO dto = initDTO(new UserDTO());
        dto.setFirstName(firstName);
        dto.setLastName(lastName);
        dto.setLoginId(loginId);
        dto.setPassword(password);
        dto.setDob(dob);
        dto.setRole(role);
        
        if ("merchant".equalsIgnoreCase(role)) {
            dto.setBusinessName(businessName);
            dto.setGstNumber(gstNumber);
            dto.setBusinessAddress(businessAddress);
        } else if ("consumer".equalsIgnoreCase(role)) {
            dto.setDeliveryAddress(deliveryAddress);
            dto.setContactNumber(contactNumber);
        }

        return dto;
    }
}
