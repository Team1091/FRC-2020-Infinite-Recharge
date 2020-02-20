package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.util.PIDTuner;

public abstract class PIDTunableSubsystem extends SubsystemBase {
    public abstract PIDTuner[] getPidTuners();
}
