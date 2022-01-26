// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class MechTrain extends SubsystemBase {
  private final CANSparkMax frontLeft = new CANSparkMax(1, MotorType.kBrushless);
  private final CANSparkMax frontRight = new CANSparkMax(2, MotorType.kBrushless);
  private final CANSparkMax backLeft = new CANSparkMax(3, MotorType.kBrushless);
  private final CANSparkMax backRight = new CANSparkMax(4, MotorType.kBrushless);

  private final MecanumDrive mechDrive = new MecanumDrive(frontLeft, backLeft, frontRight, backRight);
  /** Creates a new MechTrain. */
  public void mecanumDrive(double x, double y){
    mechDrive.driveCartesian(y, x, 0);
  }
  public void quickTurn(double rot){
    mechDrive.driveCartesian(0, 0, rot);
  }
}
