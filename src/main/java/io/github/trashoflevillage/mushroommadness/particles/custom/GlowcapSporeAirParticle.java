package io.github.trashoflevillage.mushroommadness.particles.custom;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.util.math.MathHelper;

public class GlowcapSporeAirParticle extends SpriteBillboardParticle {
    GlowcapSporeAirParticle(ClientWorld world, SpriteProvider spriteProvider, double x, double y, double z, double velocityX, double velocityY, double velocityZ) {
        super(world, x, y - 0.125, z, velocityX, velocityY, velocityZ);
        this.setBoundingBoxSpacing(0.01F, 0.01F);
        this.setSprite(spriteProvider);
        this.scale = this.scale * (this.random.nextFloat() * 0.4F + 0.6F);
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
    public int getBrightness(float tint) {
        return 15728880;
    }

    @Environment(EnvType.CLIENT)
    public static class GlowcapSporeAirFactory implements ParticleFactory<SimpleParticleType> {
        private final SpriteProvider spriteProvider;

        public GlowcapSporeAirFactory(SpriteProvider spriteProvider) {
            this.spriteProvider = spriteProvider;
        }

        public Particle createParticle(SimpleParticleType simpleParticleType, ClientWorld clientWorld, double d, double e, double f, double g, double h, double i) {
            GlowcapSporeAirParticle p = new GlowcapSporeAirParticle(clientWorld, this.spriteProvider, d, e, f, 0.0, -0.8F, 0.0);
            p.maxAge = MathHelper.nextBetween(clientWorld.random, 500, 1000);
            p.gravityStrength = 0.01F;
            p.setColor(1F, 0.97F, 0.24F);
            return p;
        }
    }
}
