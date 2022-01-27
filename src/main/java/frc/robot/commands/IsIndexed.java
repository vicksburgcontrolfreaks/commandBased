package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.subsystems.Collector;
import frc.robot.subsystems.Distance;
public class IsIndexed {
    public boolean isIndexed(Collector subsystem, double ballDist, double indexDist){
        
    if(ballDist < indexDist)
      return true;
    else
      return false;



      }
}
