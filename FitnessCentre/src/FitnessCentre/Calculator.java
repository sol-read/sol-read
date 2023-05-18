package FitnessCentre;

public interface Calculator <T extends Number> {
    double calculateFees(T clubId);
}
