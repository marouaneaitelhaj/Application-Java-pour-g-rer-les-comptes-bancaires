package org.example.Service;

import org.example.Entity.Compte;
import org.example.Entity.Virement;
import org.example.Exceptions.MyException;
import org.example.Interfaces.CompteInter;
import org.example.Interfaces.VirementInter;

import java.util.Optional;

public class ViremantService {
    private final VirementInter virementInter;
    private final CompteInter compteInter;

    public ViremantService(VirementInter virementInter, CompteInter compteInter) {
        this.virementInter = virementInter;
        this.compteInter = compteInter;
    }

    public boolean save(Virement virement) throws MyException {
        Optional<Compte> compteEmetteurOptional = compteInter.findOne(virement.getCompteEmetteur());
        Optional<Compte> compteDestinataireOptional = compteInter.findOne(virement.getCompteDestinataire());
        if (compteDestinataireOptional.isPresent() && compteEmetteurOptional.isPresent()) {
            Compte emetteur = compteEmetteurOptional.get();
            Compte destinataire = compteDestinataireOptional.get();
            if (emetteur.getSolde() >= virement.getMantant()) {
                emetteur.setSolde(emetteur.getSolde() - virement.getMantant());
                destinataire.setSolde(destinataire.getSolde() + virement.getMantant());
                virement.setCompteEmetteur(emetteur);
                virement.setCompteDestinataire(destinataire);
                if (virementInter.save(virement).isEmpty()) {
                    return false;
                } else {
                    return true;
                }
            } else {
                throw new MyException("tu n'as pas assez d'argent");
            }
        }
        return false;
    }

    public boolean delete(Virement virement) throws MyException {
        if (virement.getCompteDestinataire().getNumero().isEmpty() && virement.getCompteEmetteur().getNumero().isEmpty()) {
            throw new MyException("Le champ de code est vide");
        } else {
            if (virementInter.delete(virement)==1){
                return true;
            }else {
                return false;
            }
        }
    }
}
