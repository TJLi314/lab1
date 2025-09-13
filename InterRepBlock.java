public class InterRepBlock {
    private Integer SR;
    private Integer VR;
    private Integer PR;
    private Integer NU;

    public InterRepBlock() {
        this.SR = null;
        this.VR = null;
        this.PR = null;
        this.NU = null;
    }

    public InterRepBlock(int SR) {
        this.SR = SR;
        this.VR = null;
        this.PR = null;
        this.NU = null;
    }

    public InterRepBlock(int SR, int VR, int PR, int NU) {
        this.SR = SR;
        this.VR = VR;
        this.PR = PR;
        this.NU = NU;
    }

    public Integer getSR() {
        return this.SR;
    }

    public Integer getVR() {
        return this.VR;
    }

    public Integer getPR() {
        return this.PR;
    }

    public Integer getNU() {
        return this.NU;
    }

    public void setSR(int SR) {
        this.SR = SR;
    }

    public void setVR(int VR) {
        this.VR = VR;
    }

    public void setPR(int PR) {
        this.PR = PR;
    }

    public void setNU(int NU) {
        this.NU = NU;
    }

    @Override
    public String toString() {
        if (VR != null && PR != null && NU != null) {
            return "{" +
                    "SR=" + SR +
                    ", VR=" + VR +
                    ", PR=" + PR +
                    ", NU=" + NU +
                    '}';
        } else {
            return "{" +
                    "SR=" + SR +
                    '}';
        }
    }
}   