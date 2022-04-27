// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.CANConstants;

public class Flopper extends SubsystemBase {
  /** Creates a new Flopper. This is the mechanism that moves the Collector from the storage position to the collecting position. 
      Consider putting mechanisms like this that only opperate alongside another mechanism in the same subsystem.
  */
  private final Solenoid flopper = new Solenoid(PneumaticsModuleType.REVPH, CANConstants.flopSolenoid);

  public void flopOut(){
    //moves the Flopper to the out position
    flopper.set(true);
  }

  public void flopIn(){
    //moves the flopper to the in position
    flopper.set(false);
  }

}