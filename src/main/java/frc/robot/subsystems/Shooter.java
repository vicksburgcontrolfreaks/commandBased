// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.CANConstants;
import frc.robot.Constants.LimelightConstants;
import frc.robot.Constants.ShooterConstants;

public class Shooter extends SubsystemBase {
  // Creates a new Shooter. This is the set of motors that are used to launch cargo into the hub.
  //Both motors are defined here as there is never a case where we want to run one without the other
  private final CANSparkMax shooterL = new CANSparkMax(CANConstants.shooterL, MotorType.kBrushless);
  private final CANSparkMax shooterR = new CANSparkMax(CANConstants.shooterR, MotorType.kBrushless);

  //creates an encoder for the right shooter wheel to track the speed of both and PID Controllers for both motors
  private final RelativeEncoder shooterE = shooterR.getEncoder();
  private final SparkMaxPIDController leftP = shooterL.getPIDController();
  private final SparkMaxPIDController rightP = shooterR.getPIDController();

  //makes the 2 motors always run together and allows you to set both more easily.
  private final MotorControllerGroup shooter = new MotorControllerGroup(shooterL, shooterR);

  public Shooter(){
    //Inverts the right motor so that the motors do not run against each other and sets the initial wanted speed to 0.
    shooterR.setInverted(true);
    ShooterConstants.targetSpeed = 0;
  }

  @Override
  public void periodic() {
    //periodically checks the current shooter speed and if it is at high enough speed to effectivley launch cargo
    shooterSpeed();
    shooterPrimed();

    //sends these values to the Smart Dashboard
    SmartDashboard.putNumber("MinSpeed", ShooterConstants.minSpeed);
    SmartDashboard.putBoolean("Shooter Primed?", shooterPrimed());
    SmartDashboard.putNumber("desired Output", distanceSpeed());
    SmartDashboard.putNumber("shooterSpeed", shooterSpeed());
  }


  public void shooterMove(double speed){
    //sets the speed of the shooter to an input value and establishes what speed we want in RPM based on this input value
    shooter.set(speed);
    ShooterConstants.targetSpeed = speed * ShooterConstants.speedMultiplier;
  }

  public double shooterSpeed(){
    //returns the current speed of the shooter
    //If I had called the other encoder motor, this would not need the negative sign.
    return -shooterE.getVelocity();
  }

  public void setShootPids(int slot, double kMaxOutput, double kMinOutput){
    //sets the PID values for both motors. These will always be the same as the 2 motors are run together.
    leftP.setP(ShooterConstants.shoot_kP, slot);
    leftP.setI(ShooterConstants.shoot_kI, slot);
    leftP.setD(ShooterConstants.shoot_kD, slot);
    leftP.setIZone(ShooterConstants.shoot_kIz, slot);
    leftP.setFF(ShooterConstants.shoot_kFF, slot);
    leftP.setSmartMotionAllowedClosedLoopError(ShooterConstants.shoot_encoderError, slot);
    leftP.setOutputRange(kMinOutput, kMaxOutput, slot);

    rightP.setP(ShooterConstants.shoot_kP, slot);
    rightP.setI(ShooterConstants.shoot_kI, slot);
    rightP.setD(ShooterConstants.shoot_kD, slot);
    rightP.setIZone(ShooterConstants.shoot_kIz, slot);
    rightP.setFF(ShooterConstants.shoot_kFF, slot);
    rightP.setSmartMotionAllowedClosedLoopError(ShooterConstants.shoot_encoderError, slot);
    rightP.setOutputRange(kMinOutput, kMaxOutput, slot);
  }

  public void setSpeed(double speed){
    //Sets the speed in RPM using PID values. This is a notable example of code that does not work
    setShootPids(0, 1, -1);
    rightP.setReference(speed, ControlType.kVelocity, 0);
    leftP.setReference(speed, ControlType.kVelocity, 0);
  }

  public double distanceSpeed(){
    //returns the ideal power for the shooters to run at based on distance from the target. 
    //There is apparantely a way to greatly improve the efficiency of this through "Linear Interpolation"
    double speed = -(6000*Math.sin(.000097861*LimelightConstants.currentDistance - 1.57949) + 6000.54);
    if(speed <-1)
      return -1;
    else
      return speed;
  }

  public boolean shooterPrimed(){
    //returns whether the shooter has reached the speed needed to launch cargo or not
    if(shooterSpeed() >= ShooterConstants.minSpeed)
      return true;
    else
      return false;
  }
}