// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class MechTrain extends SubsystemBase {
  private final CANSparkMax frontLeft = new CANSparkMax(1);
  private final MecanumDrive MechDrive = new MecanumDrive;
  /** Creates a new MechTrain. */
  public MechTrain() {

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
