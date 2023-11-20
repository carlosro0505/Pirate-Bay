//objects to be used for the TableView in Search.java
public class Data {
            private String category;
            private String name;
            private String date;
            private String size;
            private String SE;
            private String LE;
            private String uploadBy;
        
            // Constructor
            public Data(String category, String name, String date, String size, String SE, String LE, String uploadBy) {
                this.category = category;
                this.name = name;
                this.date = date;
                this.size = size;
                this.SE = SE;
                this.LE = LE;
                this.uploadBy = uploadBy;
            }
        
            // Getters and Setters
        
            public String getCategory() {
                return category;
            }
        
            public void setCategory(String category) {
                this.category = category;
            }
        
            public String getName() {
                return name;
            }
        
            public void setName(String name) {
                this.name = name;
            }
        
            public String getDate() {
                return date;
            }
        
            public void setDate(String date) {
                this.date = date;
            }
        
            public String getSize() {
                return size;
            }
        
            public void setSize(String size) {
                this.size = size;
            }
        
            public String getSE() {
                return SE;
            }
        
            public void setSE(String SE) {
                this.SE = SE;
            }
        
            public String getLE() {
                return LE;
            }
        
            public void setLE(String LE) {
                this.LE = LE;
            }
        
            public String getUploadBy() {
                return uploadBy;
            }
        
            public void setUploadBy(String uploadBy) {
                this.uploadBy = uploadBy;
            }
        }