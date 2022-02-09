// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.CANConstants;

public class Flopper extends SubsystemBase {
  /** Creates a new Flopper. Both cylinders are run from the same double solenoid*/
  private final DoubleSolenoid flopper = new DoubleSolenoid(PneumaticsModuleType.REVPH, CANConstants.flopSolenoid1, CANConstants.flopSolenoid2);

  public void flopUp(){
    //moves both Floppers to the up position
    flopper.set(Value.kForward);
  }

  public void flopDown(){
    //moves both Floppers to the down position
    flopper.set(Value.kReverse);
  }

  public void flopOff(){
    //turns both Floppers off. This code is unlikely to be used regularly but is necessary for our emergency stop.
    flopper.set(Value.kOff);
  }
}