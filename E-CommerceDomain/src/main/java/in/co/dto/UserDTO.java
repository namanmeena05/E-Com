package in.co.dto;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import in.co.common.BaseDTO;

@Entity
@Table(name = "USER")
public class UserDTO extends BaseDTO {

    @Column(name = "FIRST_NAME", length = 50)
    private String firstName;

    @Column(name = "LAST_NAME", length = 50)
    private String lastName;

    @Column(name = "LOGINID", length = 50, unique = true)
    private String loginId;

    @Column(name = "PASSWORD", length = 50)
    private String password;

    @Column(name = "ROLE", length = 50)  // Values: "MERCHANT" or "CONSUMER"
    private String role;

    @Column(name = "DOB")
    private Date dob;

    // Merchant-specific attributes
    @Column(name = "BUSINESS_NAME", length = 100, nullable = true)
    private String businessName;

    @Column(name = "GST_NUMBER", length = 50, nullable = true)
    private String gstNumber;

    @Column(name = "BUSINESS_ADDRESS", length = 255, nullable = true)
    private String businessAddress;

    // Consumer-specific attributes (optional)
    @Column(name = "DELIVERY_ADDRESS", length = 255, nullable = true)
    private String deliveryAddress;

    @Column(name = "CONTACT_NUMBER", length = 15, nullable = true)
    private String contactNumber;

    // Getters and Setters

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getGstNumber() {
        return gstNumber;
    }

    public void setGstNumber(String gstNumber) {
        this.gstNumber = gstNumber;
    }

    public String getBusinessAddress() {
        return businessAddress;
    }

    public void setBusinessAddress(String businessAddress) {
        this.businessAddress = businessAddress;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    @Override
    public String getValue() {
        return loginId;  // Unique identifier for user
    }
}
