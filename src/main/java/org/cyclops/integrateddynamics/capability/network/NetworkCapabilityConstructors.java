package org.cyclops.integrateddynamics.capability.network;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.cyclops.cyclopscore.modcompat.capabilities.DefaultCapabilityProvider;
import org.cyclops.integrateddynamics.Reference;
import org.cyclops.integrateddynamics.api.network.AttachCapabilitiesEventNetwork;
import org.cyclops.integrateddynamics.api.network.INetwork;
import org.cyclops.integrateddynamics.core.network.EnergyNetwork;
import org.cyclops.integrateddynamics.core.network.PartNetwork;

/**
 * Constructor event for network capabilities.
 * @author rubensworks
 */
public class NetworkCapabilityConstructors {

    @SubscribeEvent
    public void onNetworkLoad(AttachCapabilitiesEventNetwork event) {
        INetwork network = event.getNetwork();
        PartNetwork partNetwork = new PartNetwork();
        EnergyNetwork energyNetwork = new EnergyNetwork();
        event.addCapability(new ResourceLocation(Reference.MOD_ID, "partNetwork"),
                new DefaultCapabilityProvider<>(PartNetworkConfig.CAPABILITY, partNetwork));
        event.addCapability(new ResourceLocation(Reference.MOD_ID, "energyNetwork"),
                new DefaultCapabilityProvider<>(EnergyNetworkConfig.CAPABILITY, energyNetwork));
        event.addCapability(new ResourceLocation(Reference.MOD_ID, "energyStorageNetwork"),
                new DefaultCapabilityProvider<>(CapabilityEnergy.ENERGY, energyNetwork));
        event.addFullNetworkListener(partNetwork);
        event.addFullNetworkListener(energyNetwork);
    }

}
