// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.CANConstants;

public class Reacher extends SubsystemBase {
  /** Creates a new Reacher. Both cylinders are run from the same double solenoid*/
  private final Solenoid reacher = new Solenoid(PneumaticsModuleType.REVPH, CANConstants.reachSolenoid);

  public void reachUp(){
    //moves the reacher to the up position
    reacher.set(false);
  }

  public void reachDown(){
    //moves the reacher to the down position
    reacher.set(true);
  }

}