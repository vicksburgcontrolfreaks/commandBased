// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.Constants.TestConstants;
import frc.robot.commands.AutoTurret;
import frc.robot.commands.CollectorRun;
import frc.robot.commands.DriveMech;
import frc.robot.commands.FlopUp;
import frc.robot.commands.ManualTurret;
import frc.robot.commands.QuickTurn;
import frc.robot.commands.ReachUp;
import frc.robot.commands.ShooterRun;
import frc.robot.commands.TurnDegrees;
import frc.robot.commands.WinchRun;
import frc.robot.commands.WinchUp;
import frc.robot.subsystems.Collector;
import frc.robot.subsystems.Flopper;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.MechTrain;
import frc.robot.subsystems.Reacher;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Turret;
import frc.robot.subsystems.Winch;
import edu.wpi.first.wpilibj2.command.ScheduleCommand;
import edu.wpi.first.wpilibj2.command.StartEndCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final Flopper m_Flopper = new Flopper();
  private final Reacher m_Reacher = new Reacher();
  private final MechTrain m_Drive = new MechTrain();
  private final Winch m_Winch = new Winch();
  private final Collector m_Collector = new Collector();
  private final Shooter m_Shooter = new Shooter();
  private final Turret m_Turret = new Turret();
  private final Limelight m_Limelight = new Limelight();
  private final Joystick driveStick = new Joystick(0);
  private final Joystick shootStick = new Joystick(1);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();

    m_Drive.setDefaultCommand(new DriveMech(m_Drive, driveStick::getX, driveStick::getY));
    m_Drive.setDefaultCommand(new AutoTurret(m_Turret, m_Limelight));

  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    new JoystickButton(driveStick, 3).whenPressed(new FlopUp(m_Flopper));
    new JoystickButton(driveStick, 5).whenPressed(new ReachUp(m_Reacher));
    new JoystickButton(driveStick, 2).whenPressed(new QuickTurn(m_Drive, driveStick::getX));
    new JoystickButton(shootStick, 4).whileHeld(new WinchUp(m_Winch, m_Flopper, m_Reacher, TestConstants.winchF));
    new JoystickButton(driveStick, 12).whileHeld(new WinchRun(m_Winch, TestConstants.winchB));
    new JoystickButton(shootStick, 3).whileHeld(new CollectorRun(m_Collector, TestConstants.collectF));
    new JoystickButton(shootStick, 5).whileHeld(new CollectorRun(m_Collector, TestConstants.collectB));
    new JoystickButton(shootStick, 7).whenPressed(new ShooterRun(m_Shooter, TestConstants.shootF));
    new JoystickButton(shootStick, 8).whenPressed(new ShooterRun(m_Shooter, 0));
    new JoystickButton(shootStick, 9).whenPressed(new ManualTurret(m_Turret, shootStick::getX));
    new JoystickButton(shootStick, 10).whenPressed(new AutoTurret(m_Turret, m_Limelight));

  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
}
