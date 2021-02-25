package com.example.emory;

public class Activities {
        private String name;
        private int icon;

        public Activities(String name, int icon) {
                this.name = name;
                this.icon = icon;
        }

        public String toString() {
                return this.name;
        }

        public String getName() {
                return this.name;
        }

        public int getIcon() {
                return this.icon;
        }
}
