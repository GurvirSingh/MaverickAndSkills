package com.example.maverickandskills;

public class Modeluser
    {

    String name,email,phone,image,search,cover,skills,uid;


        public Modeluser()
        {

        }

        public Modeluser(String name, String email, String phone, String image, String search, String cover, String skills, String uid) {
            this.name = name;
            this.email = email;
            this.phone = phone;
            this.image = image;
            this.search = search;
            this.cover = cover;
            this.skills = skills;
            this.uid = uid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getSearch() {
            return search;
        }

        public void setSearch(String search) {
            this.search = search;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public String getSkills() {
            return skills;
        }

        public void setSkills(String skills) {
            this.skills = skills;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }
    }
