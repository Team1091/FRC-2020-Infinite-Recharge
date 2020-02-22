package frc.robot.util;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpiutil.math.MathUtil;

public class AccelerationCurve {
    private Timer timer = new Timer();
    private double currentSpeed = 0;
    private double acceleration;
    private double minimumPower;
    private double maximumPower;

    public AccelerationCurve(double acceleration, double minimumPower, double maximumPower) {
        timer.start();
        this.acceleration = acceleration;
        this.minimumPower = minimumPower;
        this.maximumPower = maximumPower;
    }

    public void set(double input) {
        set(input, minimumPower, maximumPower);
    }

    public void set(double input, double minPowerOverride, double maxPowerOverride){
        double seconds = timer.get();
        timer.reset();

        double velocityChange = acceleration * seconds;
        if (input == 0)
            currentSpeed = 0;
        else if (input > 0) {
            if (currentSpeed >= 0) {
                currentSpeed = (input > currentSpeed) ? currentSpeed + velocityChange : input;
                currentSpeed = MathUtil.clamp(currentSpeed, minPowerOverride, maxPowerOverride);
            }
            else
                currentSpeed = minPowerOverride;

        } else if (input < 0) {
            if (currentSpeed <= 0) {
                currentSpeed = (input < currentSpeed) ? currentSpeed - velocityChange : input;
                currentSpeed = MathUtil.clamp(currentSpeed, -maxPowerOverride, -minPowerOverride);
            }
            else
                currentSpeed = -minPowerOverride;
        }
    }

    public double getCurrentSpeed() {
        return currentSpeed;
    }
    public double getMinimumPower() { return minimumPower; }
    public double getMaximumPower() { return maximumPower; }
}

