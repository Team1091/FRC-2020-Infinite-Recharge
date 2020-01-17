package frc.robot.util;

import lombok.Value;

@Value
public class VisionContour {
    double centerX;
    double centerY;
    double width;
    double area;
    double height;
    double solidity;
}
