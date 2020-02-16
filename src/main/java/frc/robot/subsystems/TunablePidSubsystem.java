package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class TunablePidSubsystem extends SubsystemBase {
    private CANPIDController pidController;
    private CANEncoder encoder;
    private int canID;
    private double speed;
    // PID coefficients
    private double kP;
    private double kI;
    private double kD;
    private double kIz;
    private double kFF;
    private double kMaxOutput;
    private double kMinOutput;
    private double maxRPM;

    public TunablePidSubsystem(){

        //TODO: Make a new tab for SmartDashboard
//        SmartDashboard.
    }

    public void enableTune(CANSparkMax motor, int canID, double speed, double kP, double kI, double kD,
                            double kIz, double kFF, double kMaxOutput, double kMinOutput, double maxRPM) {
        this.pidController = motor.getPIDController();
        this.encoder = motor.getEncoder();
        this.canID = canID;
        this.speed = speed;
        this.kP = kP;
        this.kI = kI;
        this.kD = kD;
        this.kIz = kIz;
        this.kFF = kFF;
        this.kMaxOutput = kMaxOutput;
        this.kMinOutput = kMinOutput;
        this.maxRPM = maxRPM;

        //These need to run once
        SmartDashboard.putNumber("P Gain " + canID, kP);
        SmartDashboard.putNumber("I Gain " + canID, kI);
        SmartDashboard.putNumber("D Gain " + canID, kD);
        SmartDashboard.putNumber("I Zone " + canID, kIz);
        SmartDashboard.putNumber("Feed Forward " + canID, kFF);
        SmartDashboard.putNumber("Max Output " + canID, kMaxOutput);
        SmartDashboard.putNumber("Min Output " + canID, kMinOutput);
    }
    public void tunePeriodic(){
        // read PID coefficients from SmartDashboard
        double p = SmartDashboard.getNumber("P Gain "+canID, 0);
        double i = SmartDashboard.getNumber("I Gain "+canID, 0);
        double d = SmartDashboard.getNumber("D Gain "+canID, 0);
        double iz = SmartDashboard.getNumber("I Zone "+canID, 0);
        double ff = SmartDashboard.getNumber("Feed Forward "+canID, 0);
        double max = SmartDashboard.getNumber("Max Output "+canID, 0);
        double min = SmartDashboard.getNumber("Min Output "+canID, 0);

        // if PID coefficients on SmartDashboard have changed, write new values to controller
        if((p != kP)) { pidController.setP(p); kP = p; }
        if((i != kI)) { pidController.setI(i); kI = i; }
        if((d != kD)) { pidController.setD(d); kD = d; }
        if((iz != kIz)) { pidController.setIZone(iz); kIz = iz; }
        if((ff != kFF)) { pidController.setFF(ff); kFF = ff; }
        if((max != kMaxOutput) || (min != kMinOutput)) {
            pidController.setOutputRange(min, max);
            kMinOutput = min; kMaxOutput = max;
        }

        /**
         * PIDController objects are commanded to a set point using the
         * SetReference() method.
         *
         * The first parameter is the value of the set point, whose units vary
         * depending on the control type set in the second parameter.
         *
         * The second parameter is the control type can be set to one of four
         * parameters:
         *  com.revrobotics.ControlType.kDutyCycle
         *  com.revrobotics.ControlType.kPosition
         *  com.revrobotics.ControlType.kVelocity
         *  com.revrobotics.ControlType.kVoltage
         */
        double setPoint = speed*maxRPM; //TODO: possibly move this so that this command is not taking in the speed every time it's scheduled
        pidController.setReference(setPoint, ControlType.kVelocity);

        SmartDashboard.putNumber("SetPoint", setPoint);
        SmartDashboard.putNumber("ProcessVariable", encoder.getVelocity());
    }
}
