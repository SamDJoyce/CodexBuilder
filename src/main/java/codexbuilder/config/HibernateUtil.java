package codexbuilder.config;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import codexbuilder.model.StatLineInfantry;
import codexbuilder.model.StatLineVehicle;
import codexbuilder.model.StatLineWalker;
import codexbuilder.model.WargearDescription;
import codexbuilder.model.effects.AddGearToSquadEffect;
import codexbuilder.model.effects.AddModelEffect;
import codexbuilder.model.effects.AddModelTypeEffect;
import codexbuilder.model.effects.AddWargearEffect;
import codexbuilder.model.effects.ChangeModelNameEffect;
import codexbuilder.model.effects.ModifyStatEffect;
import codexbuilder.model.effects.RemoveWargearEffect;
import codexbuilder.model.effects.ReplaceModelEffect;
import codexbuilder.model.effects.ReplaceWargearEffect;
import codexbuilder.model.requirements.ArmouryPointsLimitReq;
import codexbuilder.model.requirements.ArmouryWeaponLimitReq;
import codexbuilder.model.requirements.CannotHaveGearReq;
import codexbuilder.model.requirements.CharactersOnlyReq;
import codexbuilder.model.requirements.MaxPerModelCountReq;
import codexbuilder.model.requirements.MaxSelectionReq;
import codexbuilder.model.requirements.ModelCountReq;
import codexbuilder.model.requirements.MustHaveGearReq;
import codexbuilder.model.requirements.MustHaveTypeReq;
import codexbuilder.model.requirements.MustStartWithGearReq;

public class HibernateUtil {

    private static final SessionFactory SESSION_FACTORY =
        buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            return new Configuration()
            	// Wargear
                .addAnnotatedClass(WargearDescription.class)
                // StatLines
                .addAnnotatedClass(StatLineInfantry.class)
                .addAnnotatedClass(StatLineVehicle.class)
                .addAnnotatedClass(StatLineWalker.class)
                // Requirements
                .addAnnotatedClass(ArmouryPointsLimitReq.class)
                .addAnnotatedClass(ArmouryWeaponLimitReq.class)
                .addAnnotatedClass(CannotHaveGearReq.class)
                .addAnnotatedClass(CharactersOnlyReq.class)
                .addAnnotatedClass(MaxPerModelCountReq.class)
                .addAnnotatedClass(MaxSelectionReq.class)
                .addAnnotatedClass(ModelCountReq.class)
                .addAnnotatedClass(MustHaveGearReq.class)
                .addAnnotatedClass(MustHaveTypeReq.class)
                .addAnnotatedClass(MustStartWithGearReq.class)
                // Effects
                .addAnnotatedClass(AddGearToSquadEffect.class)
                .addAnnotatedClass(AddModelEffect.class)
                .addAnnotatedClass(AddModelTypeEffect.class)
                .addAnnotatedClass(AddWargearEffect.class)
                .addAnnotatedClass(ChangeModelNameEffect.class)
                .addAnnotatedClass(ModifyStatEffect.class)
                .addAnnotatedClass(RemoveWargearEffect.class)
                .addAnnotatedClass(ReplaceModelEffect.class)
                .addAnnotatedClass(ReplaceWargearEffect.class)
                
                .buildSessionFactory();

        } catch (Throwable ex) {
            System.err.println(
                "SessionFactory creation failed: " + ex
            );

            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return SESSION_FACTORY;
    }

    public static void shutdown() {
        getSessionFactory().close();
    }
}