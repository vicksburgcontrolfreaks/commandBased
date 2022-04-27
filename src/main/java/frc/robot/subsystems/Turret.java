// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.CANConstants;
import frc.robot.Constants.LimelightConstants;

public class Turret extends SubsystemBase {
  //Creates a new Turret. This is the motor that runs the rotating turret that lets us maintain targeting towards the hub.
  private final CANSparkMax turret = new CANSparkMax(CANConstants.turret, MotorType.kBrushless);
  private final RelativeEncoder turretE = turret.getEncoder();

  public Turret(){  
    //resets the turret encoder on initialization. If the turret is set wrong, certain functions will not work properly. 
    turretE.setPosition(0);
  }

  public void runTurret(double speed){
    //runs the turret at a set speed
    turret.set(speed);
  }

  @Override
  public void periodic() {
    //periodically checks the current position and speed of the encoder
    turretEncoderP();
    turretEncoderV();
  }

  public double turretEncoderP(){
    //returns the current position of the turret
    return turretE.getPosition();
  }

  public double turretEncoderV(){
    //returns the current speed of the turret. Here, V means velocity and not value. Again, I'm bad at names.
    return turretE.getVelocity();
  }

  public void turretReset(){
    //sets the turret encoder to 0
    turretE.setPosition(0);
  }

  public boolean turretPrimed(){
    //checks if the turret is centered so that the lifter can move to the up position
    if(Math.abs(turretEncoderP()) < LimelightConstants.error)
      return true;
    else 
      return false;
  }

}