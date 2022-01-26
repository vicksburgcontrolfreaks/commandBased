// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.CollectorRun;
import frc.robot.commands.DriveMech;
import frc.robot.commands.FlopUp;
import frc.robot.commands.QuickTurn;
import frc.robot.commands.ReachUp;
import frc.robot.commands.ShooterRun;
import frc.robot.commands.WinchRun;
import frc.robot.commands.WinchUp;
import frc.robot.subsystems.Collector;
import frc.robot.subsystems.Flopper;
import frc.robot.subsystems.MechTrain;
import frc.robot.subsystems.Reacher;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Winch;
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
  private final Joystick driveStick = new Joystick(0);
  private final Joystick shootStick = new Joystick(1);




  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();

    m_Drive.setDefaultCommand(new DriveMech(m_Drive, driveStick::getX, driveStick::getY));

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
    new JoystickButton(shootStick, 4).whenHeld(new WinchUp(m_Winch, 1, m_Flopper, m_Reacher));
    new JoystickButton(driveStick, 12).whenHeld(new WinchRun(m_Winch, -.25));
    new JoystickButton(shootStick, 3).whenHeld(new CollectorRun(m_Collector, .75));
    new JoystickButton(shootStick, 5).whenHeld(new CollectorRun(m_Collector, -1));
    new JoystickButton(shootStick, 7).whenPressed(new ShooterRun(m_Shooter, 1));
    new JoystickButton(shootStick, 8).whenPressed(new ShooterRun(m_Shooter, 0));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
}
