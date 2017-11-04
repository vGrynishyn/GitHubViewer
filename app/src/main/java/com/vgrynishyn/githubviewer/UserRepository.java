package com.vgrynishyn.githubviewer;

import java.io.Serializable;

/**
 * Created by VGrynishyn on 11/3/2017.
 */

public class UserRepository implements Serializable {

        private String name;
        private String description;
        private String language;
        private String stars;
        private String forks;
        private String updateDate;

       public UserRepository() {}

       public UserRepository(String name, String description, String language, String stars, String forks, String updateDate) {
            this.name = name;
            this.description = description;
            this.language = language;
            this.stars = stars;
            this.forks = forks;
            this.updateDate = updateDate;
       }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getLanguage() {
            return language;
        }

        public void setLanguage(String language) {
            this.language = language;
        }

        public String getStars() {
            return stars;
        }

        public void setStars(String stars) {
            this.stars = stars;
        }

        public String getForks() {
            return forks;
        }

        public void setForks(String forks) {
            this.forks = forks;
        }

        public String getUpdateDate() {
            return updateDate;
        }

        public void setUpdateDate(String updateDate) {
            this.updateDate = updateDate;
        }

    }
//}
