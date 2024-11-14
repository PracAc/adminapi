package org.oz.adminapi.adminlogin.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = {""})
@Table(name="admin_login")
public class AdminLoginEntity {

    @Id
    @Column(name = "adminId", nullable = false)
    private String adminId;

    @Column(name="adminPw", nullable = false)
    private String adminPw;

    @Column(name="adminName", nullable = false)
    private String adminName;
}
