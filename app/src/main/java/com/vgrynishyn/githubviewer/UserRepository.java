package com.vgrynishyn.githubviewer;

/**
 * Created by VGrynishyn on 11/3/2017.
 */

public class UserRepository {


    //public class ReposInfo {
        private String name;
        private String description;
        private String language;
        private int stars;
        private int forks;
        private String updateDate;

//        public ReposInfo() {
//        }

       public UserRepository() {
      }

        public UserRepository(String name, String description, String language, int stars, int forks, String updateDate) {
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

        public int getStars() {
            return stars;
        }

        public void setStars(int stars) {
            this.stars = stars;
        }

        public int getForks() {
            return forks;
        }

        public void setForks(int forks) {
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
