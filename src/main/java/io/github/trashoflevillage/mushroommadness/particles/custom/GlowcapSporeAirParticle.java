package io.github.trashoflevillage.mushroommadness.particles.custom;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.SimpleParticleType;

public class GlowcapSporeAirParticle extends SpriteBillboardParticle {
    GlowcapSporeAirParticle(ClientWorld world, SpriteProvider spriteProvider, double x, double y, double z) {
        super(world, x, y - 0.125, z);
        this.setBoundingBoxSpacing(0.01F, 0.01F);
        this.setSprite(spriteProvider);
        this.scale = this.scale * (this.random.nextFloat() * 0.6F + 0.2F);
        this.maxAge = (int)(16.0 / (Math.random() * 0.8 + 0.2));
        this.collidesWithWorld = false;
        this.velocityMultiplier = 1.0F;
        this.gravityStrength = 0.0F;
    }

    GlowcapSporeAirParticle(ClientWorld world, SpriteProvider spriteProvider, double x, double y, double z, double velocityX, double velocityY, double velocityZ) {
        super(world, x, y - 0.125, z, velocityX, velocityY, velocityZ);
        this.setBoundingBoxSpacing(0.01F, 0.01F);
        this.setSprite(spriteProvider);
        this.scale = this.scale * (this.random.nextFloat() * 0.6F + 0.6F);
        this.maxAge = (int)(16.0 / (Math.random() * 0.8 + 0.2));
        this.collidesWithWorld = false;
        this.velocityMultiplier = 1.0F;
        this.gravityStrength = 0.0F;
    }

    @Override
    public ParticleTextureSheet getType() {
        return ParticleTextureSheet.PARTICLE_SHEET_OPAQUE;
    }

    @Override
    protected int getBrightness(float tint) {
        return 99999;
    }

    @Environment(EnvType.CLIENT)
    public static class GlowcapSporeAirFactory implements ParticleFactory<SimpleParticleType> {
        private final WaterSuspendParticle.SporeBlossomAirFactory waterSuspendParticle;

        public GlowcapSporeAirFactory(SpriteProvider spriteProvider) {
            waterSuspendParticle = new WaterSuspendParticle.SporeBlossomAirFactory(spriteProvider);
        }

        public Particle createParticle(SimpleParticleType simpleParticleType, ClientWorld clientWorld, double d, double e, double f, double g, double h, double i) {
            Particle p = waterSuspendParticle.createParticle(simpleParticleType, clientWorld, d, e, f, g, h, i);

            p.setColor(1F, 0.97F, 0.24F);
            return p;
        }
    }
}
