package org.energji;

public class SistemiEnergjise {


        private String emriZones;
        private double konsum;
        private double prodhim;
        private double kapacitetMaksimal;
        private int prioritet;
        private String status;
        private boolean rezerveAktive;


        public SistemiEnergjise() {
        }


        public SistemiEnergjise(String emriZones, double konsum, double prodhim,
                                double kapacitetMaksimal, int prioritet,
                                String status, boolean rezerveAktive) {
            this.emriZones = emriZones;
            this.konsum = konsum;
            this.prodhim = prodhim;
            this.kapacitetMaksimal = kapacitetMaksimal;
            this.prioritet = prioritet;
            this.status = status;
            this.rezerveAktive = rezerveAktive;
        }

        public String getEmriZones() {
            return emriZones;
        }

        public void setEmriZones(String emriZones) {
            this.emriZones = emriZones;
        }

        public double getKonsum() {
            return konsum;
        }

        public void setKonsum(double konsum) {
            this.konsum = konsum;
        }

        public double getProdhim() {
            return prodhim;
        }

        public void setProdhim(double prodhim) {
            this.prodhim = prodhim;
        }

        public double getKapacitetMaksimal() {
            return kapacitetMaksimal;
        }

        public void setKapacitetMaksimal(double kapacitetMaksimal) {
            this.kapacitetMaksimal = kapacitetMaksimal;
        }

        public int getPrioritet() {
            return prioritet;
        }

        public void setPrioritet(int prioritet) {
            this.prioritet = prioritet;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public boolean isRezerveAktive() {
            return rezerveAktive;
        }

        public void setRezerveAktive(boolean rezerveAktive) {
            this.rezerveAktive = rezerveAktive;
        }



}
