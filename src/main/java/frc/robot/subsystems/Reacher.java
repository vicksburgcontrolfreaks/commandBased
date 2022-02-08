// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Reacher extends SubsystemBase {
  /** Creates a new Reacher. Both cylinders are run from the same double solenoid*/
  private final DoubleSolenoid reacher = new DoubleSolenoid(PneumaticsModuleType.REVPH, 2, 3);

  public void reachUp(){
    //moves the reacher to the up position
    reacher.set(Value.kReverse);
  }

  public void reachDown(){
    //moves the reacher to the down position
    reacher.set(Value.kForward);
  }

  public void reachOff(){
    //turns reacher off. This code is unlikely to be used regularly but is necessary for our emergency stop.
    reacher.set(Value.kOff);
  }
}