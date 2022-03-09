// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.CANConstants;

public class Flopper extends SubsystemBase {
  /** Creates a new Lifter. Both cylinders are run from the same double solenoid*/
  private final Solenoid flopper = new Solenoid(PneumaticsModuleType.REVPH, CANConstants.flopSolenoid);

  public void flopOut(){
    //moves both Lifters to the up position
    flopper.set(true);
  }

  public void flopIn(){
    //moves both Lifters to the down position
    flopper.set(false);
  }
}