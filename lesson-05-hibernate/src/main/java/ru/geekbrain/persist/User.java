package ru.geekbrain.persist;

import ru.geekbrain.purchase.LineOfPurchase;
import ru.geekbrain.purchase.Purchase;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "user_tbl")
@NamedQueries({
        @NamedQuery(name = "userByName", query = "from User u where u.username=:username"),
        @NamedQuery(name = "userById", query = "from User u where u.id=:id"),
        @NamedQuery(name = "allUsers", query = "from User")
})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(unique = true, nullable = false, columnDefinition = "default 'user_id'")
    private Long user_id;

    @Column(length = 16, unique = true, nullable = false)//, columnDefinition = "default 'username_fld'")
    private String username;

    @Column(length = 32, nullable = false)//, columnDefinition = "default 'password_fld'")
    private String password;

    @Transient //для полей которые не надо хранить в БД
    private String matchingPassword;

    @Column//(length = 255, columnDefinition = "default 'email_fld'")
    private String email;

    @Column//(columnDefinition = "default 'createDate_fld'")
    private Date createDate;

    @Column//(columnDefinition = "default 'blockDate_fld'")
    private Date blockDate;

    @Column//(columnDefinition = "default 'isBlock_fld'")
    private Boolean isBlock;

    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL)
    private List<Contact> contactList;

    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL)
    private List<Purchase> purchaseList;

    public User() {
        isBlock=false;
        createDate = new Date(System.currentTimeMillis());
    }

    public User(String username) {
        this.username = username;
        isBlock=false;
        createDate = new Date(System.currentTimeMillis());
    }

    public User(String username, String password, String email) {
        this.username = username;
        this.password= password;
        this.email=email;
        isBlock=false;
        createDate = new Date(System.currentTimeMillis());
    }

    public Long getId() {
        return user_id;
    }

    public void setId(Long id) {
        this.user_id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMatchingPassword() {
        return matchingPassword;
    }

    public void setMatchingPassword(String matchingPassword) {
        this.matchingPassword = matchingPassword;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getBlockDate() {
        return blockDate;
    }

    public void setBlockDate(Date blockDate) {
        this.blockDate = blockDate;
    }

    public Boolean getIsBlock() {
        return isBlock;
    }

    public void setIsBlock(Boolean isBlock) {
        this.isBlock = isBlock;
    }

    @Override
    public String toString(){
        return "\nUser{"+
                "id="+user_id+
                ", username='"+username+ '\''+
                ", password='"+password+ '\''+
                ", email='"+email+ '\''+
                ", date created='"+createDate+ '\''+
                ", is block='"+isBlock+ '\''+
                ", date block='"+blockDate+ '\''+
                '}';
    }

    public List<Contact> getContactList() {
        return contactList;
    }

    public void setContactList(List<Contact> contactList) {
        this.contactList = contactList;
    }

    public List<Purchase> getPurchaseList() {
        return purchaseList;
    }

    public void setPurchaseList(List<Purchase> purchaseList) {
        this.purchaseList = purchaseList;
    }
}
