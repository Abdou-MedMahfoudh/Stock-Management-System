package metier;

public class Module {
    private String code;
    private int credit;
    private double coefficient;

    public Module(String code, int credit, double coefficient) {
        this.code = code;
        this.credit = credit;
        this.coefficient = coefficient;
    }

    public Module() {}

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public double getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(double coefficient) {
        this.coefficient = coefficient;
    }

    @Override
    public String toString() {
        return "Module [code=" + code + ", credit=" + credit + ", coefficient=" + coefficient + "]";
    }
}