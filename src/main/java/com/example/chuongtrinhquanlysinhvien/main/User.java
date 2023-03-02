package com.example.chuongtrinhquanlysinhvien.main;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "userdb")
public class User {
    @Id
    private Long id;
    private String hoVaTen;
    private String queQuan;
    private Integer namSinh;
    private String maSinhVien;
    private Float diemTrungBinh;
    private String password;

    public User(String maSinhVien, String password) {
        this.maSinhVien = maSinhVien;
        this.password = password;
    }
}
