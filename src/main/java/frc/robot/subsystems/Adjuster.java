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

public class Adjuster extends SubsystemBase {
  /** Creates a new Adjuster. This is the motor that runs ajdustable hood on the shooter. */
  private final CANSparkMax adjuster = new CANSparkMax(CANConstants.adjuster, MotorType.kBrushless);
  private final RelativeEncoder adjusterE = adjuster.getEncoder();

  public Adjuster(){  
    //sets the encoder values of the shooter to 0 upon initialization 
    adjusterReset();
  }

  public void runAdjuster(double speed){
    //runs the adjuster at a set speed
    adjuster.set(speed);
  }

  @Override
  public void periodic() {
    //periodically checks the current position and speed of the encoder as well as the desired position based on the distacne from the target.
    adjusterEncoderP();
    adjusterEncoderV();
    distanceAdjust();
  }

  public double adjusterEncoderP(){
    //returns the current position of the adjuster
    return adjusterE.getPosition();
  }

  public double adjusterEncoderV(){
    //returns the current speed of the adjuster
    return adjusterE.getVelocity();
  }

  public double distanceAdjust(){
    //returns the current desired location of the adjuster based on the distance from the target
    //this distance value is pulled directly from the constants so as to avoid needing to call the limelight every time the adjuster is needed.
    return 2*LimelightConstants.currentDistance;
  }

  public void adjusterReset(){
    //resets the adjuster encoder to 0
    adjusterE.setPosition(0);
  }
}
