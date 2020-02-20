package frc.robot.util;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;

public class PIDTuner {
    private String motorName;

    private CANSparkMax motor;
    private CANPIDController pidController;
    private CANEncoder encoder;

    // PID coefficients
    private double kP;
    private double kI;
    private double kD;
    private double kIz;
    private double kFF;
    private double kMaxOutput;
    private double kMinOutput;
    private double maxRPM;

    private String pKey;
    private String iKey;
    private String dKey;
    private String iZKey;
    private String fFKey;
    private String maxOutputKey;
    private String minOutputKey;
    private String maxRpmKey;
    private String speedKey;

    public PIDTuner(CANSparkMax motor, String motorName) {
        this.motorName = motorName;
        this.motor = motor;
        this.pidController = this.motor.getPIDController();
        this.encoder = motor.getEncoder();

        pKey = String.format("%s P Gain", motorName);
        iKey = String.format("%s I Gain", motorName);
        dKey = String.format("%s D Gain", motorName);
        iZKey = String.format("%s I Zone", motorName);
        fFKey = String.format("%s Feed Forward", motorName);
        maxOutputKey = String.format("%s Max Output", motorName);
        minOutputKey = String.format("%s Min Output", motorName);
        maxRpmKey = String.format("%s Max Rpm", motorName);
        speedKey = String.format("%s Target Speed", motorName);
    }

    public void init() {
        init(
            Constants.SparkMaxNeoTurningDefaults.kP,
            Constants.SparkMaxNeoTurningDefaults.kI,
            Constants.SparkMaxNeoTurningDefaults.kD,
            Constants.SparkMaxNeoTurningDefaults.kIz,
            Constants.SparkMaxNeoTurningDefaults.kFF,
            Constants.SparkMaxNeoTurningDefaults.kMaxOutput,
            Constants.SparkMaxNeoTurningDefaults.kMinOutput,
            Constants.SparkMaxNeoTurningDefaults.maxRPM
        );
    }

    public void init(double kP, double kI, double kD, double kIz, double kFF, double kMaxOutput, double kMinOutput, double maxRPM) {
        motor.restoreFactoryDefaults();
        this.maxRPM = maxRPM;

        //These need to run once
        SmartDashboard.putNumber(pKey, kP);
        SmartDashboard.putNumber(iKey, kI);
        SmartDashboard.putNumber(dKey, kD);
        SmartDashboard.putNumber(iZKey, kIz);
        SmartDashboard.putNumber(fFKey, kFF);
        SmartDashboard.putNumber(maxOutputKey, kMaxOutput);
        SmartDashboard.putNumber(minOutputKey, kMinOutput);
        SmartDashboard.putNumber(maxRpmKey, maxRPM);
        SmartDashboard.putNumber(speedKey, 0);
    }

    public void run() {
        // read PID coefficients from SmartDashboard
        double p = SmartDashboard.getNumber(pKey, 0);
        double i = SmartDashboard.getNumber(iKey, 0);
        double d = SmartDashboard.getNumber(dKey, 0);
        double iz = SmartDashboard.getNumber(iZKey, 0);
        double ff = SmartDashboard.getNumber(fFKey, 0);
        double max = SmartDashboard.getNumber(maxOutputKey, 0);
        double min = SmartDashboard.getNumber(minOutputKey, 0);
        double speed = SmartDashboard.getNumber(speedKey, 0);

        // if PID coefficients on SmartDashboard have changed, write new values to controller
        if ((p != kP)) {
            pidController.setP(p);
            kP = p;
        }
        if ((i != kI)) {
            pidController.setI(i);
            kI = i;
        }
        if ((d != kD)) {
            pidController.setD(d);
            kD = d;
        }
        if ((iz != kIz)) {
            pidController.setIZone(iz);
            kIz = iz;
        }
        if ((ff != kFF)) {
            pidController.setFF(ff);
            kFF = ff;
        }
        if ((max != kMaxOutput) || (min != kMinOutput)) {
            pidController.setOutputRange(min, max);
            kMinOutput = min;
            kMaxOutput = max;
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
        double setPoint = speed * maxRPM;
        pidController.setReference(setPoint, ControlType.kVelocity);

        SmartDashboard.putNumber(motorName + " SetPoint", setPoint);
        SmartDashboard.putNumber(motorName + " ProcessVariable", encoder.getVelocity());
    }
}
