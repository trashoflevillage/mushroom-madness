package io.github.trashoflevillage.mushroommadness.entity.custom;

import io.github.trashoflevillage.mushroommadness.items.ModItems;
import io.github.trashoflevillage.mushroommadness.items.custom.MushroomBowItem;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.RangedAttackMob;
import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.*;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.passive.MerchantEntity;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.entity.raid.RaiderEntity;
import net.minecraft.item.BowItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.DyeColor;
import net.minecraft.util.math.Box;
import net.minecraft.world.GameRules;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class MycologistEntity extends SpellcastingIllagerEntity implements RangedAttackMob {
    @Nullable
    private CowEntity cowTarget;
    private static Item[] bowTypes = new Item[] {
            ModItems.RED_MUSHROOM_BOW,
            ModItems.BROWN_MUSHROOM_BOW,
            ModItems.GLOWCAP_MUSHROOM_BOW
    };
    
    public MycologistEntity(EntityType<? extends SpellcastingIllagerEntity> entityType, World world) {
        super(entityType, world);
    }

    private void updateAnimations() {

    }

    @Override
    public void tick() {
        super.tick();
        if (this.getWorld().isClient()) {
            updateAnimations();
        }
    }

    @Override
    public void addBonusForWave(ServerWorld world, int wave, boolean unused) {

    }

    @Override
    protected void initGoals() {
        super.initGoals();
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(1, new LookAtTargetGoal());
        this.goalSelector.add(6, new MushroomBowAttackGoal<>(this, 0.5, 20, 15.0F));
        this.goalSelector.add(6, new ConvertCowGoal());
        this.goalSelector.add(8, new WanderAroundGoal(this, 0.6));
        this.goalSelector.add(9, new LookAtEntityGoal(this, PlayerEntity.class, 3.0F, 1.0F));
        this.goalSelector.add(10, new LookAtEntityGoal(this, MobEntity.class, 8.0F));
        this.targetSelector.add(1, new RevengeGoal(this, RaiderEntity.class).setGroupRevenge());
        this.targetSelector.add(2, new ActiveTargetGoal(this, PlayerEntity.class, true).setMaxTimeWithoutVisibility(300));
        this.targetSelector.add(3, new ActiveTargetGoal(this, MerchantEntity.class, false).setMaxTimeWithoutVisibility(300));
        this.targetSelector.add(3, new ActiveTargetGoal(this, IronGolemEntity.class, false).setMaxTimeWithoutVisibility(300));
    }

    public static DefaultAttributeContainer.Builder createMycologistAttributes() {
        return HostileEntity.createHostileAttributes()
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.5)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 18.0)
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 32.0);
    }

    @Override
    public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData) {
        ItemStack bow = new ItemStack(bowTypes[random.nextBetween(0, 2)]);

        this.equipStack(EquipmentSlot.MAINHAND, bow);
        return super.initialize(world, difficulty, spawnReason, entityData);
    }

    @Override
    public Box getVisibilityBoundingBox() {
        return this.getBoundingBox().expand(3.0, 0.0, 3.0);
    }

    @Override
    public SoundEvent getCelebratingSound() {
        return SoundEvents.ENTITY_ILLUSIONER_AMBIENT;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_ILLUSIONER_AMBIENT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_ILLUSIONER_DEATH;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.ENTITY_ILLUSIONER_HURT;
    }

    @Override
    protected SoundEvent getCastSpellSound() {
        return SoundEvents.ENTITY_ILLUSIONER_CAST_SPELL;
    }

    @Override
    public void shootAt(LivingEntity target, float pullProgress) {
        ItemStack itemStack = this.getStackInHand(ProjectileUtil.getHandPossiblyHolding(this, this.getHeldBow()));
        ItemStack itemStack2 = this.getProjectileType(itemStack);
        PersistentProjectileEntity persistentProjectileEntity = ((MushroomBowItem)itemStack.getItem()).getArrowEntity(getWorld(), this, itemStack2);
        double d = target.getX() - this.getX();
        double e = target.getBodyY(0.3333333333333333) - persistentProjectileEntity.getY();
        double f = target.getZ() - this.getZ();
        double g = Math.sqrt(d * d + f * f);
        persistentProjectileEntity.setVelocity(d, e + g * 0.2F, f, 1.6F, (float)(14 - this.getWorld().getDifficulty().getId() * 4));
        this.playSound(SoundEvents.ENTITY_SKELETON_SHOOT, 1.0F, 1.0F / (this.getRandom().nextFloat() * 0.4F + 0.8F));
        this.getWorld().spawnEntity(persistentProjectileEntity);
    }

    protected boolean isHoldingBow() {
        return getHeldBow() != null;
    }

    protected Item getHeldBow() {
        for (Item i : bowTypes)
            if (this.isHolding(i)) return i;
        return null;
    }

    @Override
    public State getState() {
        if (this.isSpellcasting()) {
            return State.SPELLCASTING;
        } else {
            return this.isAttacking() ? State.BOW_AND_ARROW : State.CROSSED;
        }
    }
    

    void setCowTarget(@Nullable CowEntity target) {
        this.cowTarget = target;
    }

    @Nullable
    CowEntity getCowTarget() {
        return this.cowTarget;
    }

    public class ConvertCowGoal extends CastSpellGoal {
        private final TargetPredicate convertibleCowPredicate = TargetPredicate.createNonAttackable()
                .setBaseMaxDistance(16.0)
                .setPredicate(livingEntity -> ((CowEntity)livingEntity).getType() == EntityType.COW);

        @Override
        public boolean canStart() {
            if (MycologistEntity.this.getTarget() != null) {
                return false;
            } else if (MycologistEntity.this.isSpellcasting()) {
                return false;
            } else if (MycologistEntity.this.age < this.startTime) {
                return false;
            } else if (!MycologistEntity.this.getWorld().getGameRules().getBoolean(GameRules.DO_MOB_GRIEFING)) {
                return false;
            } else {
                List<CowEntity> list = MycologistEntity.this.getWorld()
                        .getTargets(CowEntity.class, this.convertibleCowPredicate, MycologistEntity.this, MycologistEntity.this.getBoundingBox().expand(16.0, 4.0, 16.0));
                if (list.isEmpty()) {
                    return false;
                } else {
                    MycologistEntity.this.setCowTarget((CowEntity)list.get(MycologistEntity.this.random.nextInt(list.size())));
                    return true;
                }
            }
        }

        @Override
        public boolean shouldContinue() {
            return MycologistEntity.this.getCowTarget() != null && this.spellCooldown > 0;
        }

        @Override
        public void stop() {
            super.stop();
            MycologistEntity.this.setCowTarget(null);
        }

        @Override
        protected void castSpell() {
            CowEntity cowEntity = MycologistEntity.this.getCowTarget();
            if (cowEntity != null && cowEntity.isAlive()) {
                cowEntity.convertTo(EntityType.MOOSHROOM, true);
            }
        }

        @Override
        protected int getInitialCooldown() {
            return 40;
        }

        @Override
        protected int getSpellTicks() {
            return 60;
        }

        @Override
        protected int startTimeDelay() {
            return 140;
        }

        @Override
        protected SoundEvent getSoundPrepare() {
            return SoundEvents.ENTITY_EVOKER_PREPARE_WOLOLO;
        }

        @Override
        protected Spell getSpell() {
            return Spell.WOLOLO;
        }
    }


    public class MushroomBowAttackGoal<T extends MycologistEntity> extends Goal {
        private final T actor;
        private final double speed;
        private int attackInterval;
        private final float squaredRange;
        private int cooldown = -1;
        private int targetSeeingTicker;
        private boolean movingToLeft;
        private boolean backward;
        private int combatTicks = -1;

        public MushroomBowAttackGoal(T actor, double speed, int attackInterval, float range) {
            this.actor = actor;
            this.speed = speed;
            this.attackInterval = attackInterval;
            this.squaredRange = range * range;
            this.setControls(EnumSet.of(Goal.Control.MOVE, Goal.Control.LOOK));
        }

        public void setAttackInterval(int attackInterval) {
            this.attackInterval = attackInterval;
        }

        @Override
        public boolean canStart() {
            return this.actor.getTarget() == null ? false : this.isHoldingBow();
        }

        protected boolean isHoldingBow() {
            return getHeldBow() != null;
        }

        protected Item getHeldBow() {
            for (Item i : bowTypes)
                if (this.actor.isHolding(i)) return i;
            return null;
        }

        @Override
        public boolean shouldContinue() {
            return (this.canStart() || !this.actor.getNavigation().isIdle()) && this.isHoldingBow();
        }

        @Override
        public void start() {
            super.start();
            this.actor.setAttacking(true);
        }

        @Override
        public void stop() {
            super.stop();
            this.actor.setAttacking(false);
            this.targetSeeingTicker = 0;
            this.cooldown = -1;
            this.actor.clearActiveItem();
        }

        @Override
        public boolean shouldRunEveryTick() {
            return true;
        }

        @Override
        public void tick() {
            LivingEntity livingEntity = this.actor.getTarget();
            if (livingEntity != null) {
                double d = this.actor.squaredDistanceTo(livingEntity.getX(), livingEntity.getY(), livingEntity.getZ());
                boolean bl = this.actor.getVisibilityCache().canSee(livingEntity);
                boolean bl2 = this.targetSeeingTicker > 0;
                if (bl != bl2) {
                    this.targetSeeingTicker = 0;
                }

                if (bl) {
                    this.targetSeeingTicker++;
                } else {
                    this.targetSeeingTicker--;
                }

                if (!(d > (double)this.squaredRange) && this.targetSeeingTicker >= 20) {
                    this.actor.getNavigation().stop();
                    this.combatTicks++;
                } else {
                    this.actor.getNavigation().startMovingTo(livingEntity, this.speed);
                    this.combatTicks = -1;
                }

                if (this.combatTicks >= 20) {
                    if ((double)this.actor.getRandom().nextFloat() < 0.3) {
                        this.movingToLeft = !this.movingToLeft;
                    }

                    if ((double)this.actor.getRandom().nextFloat() < 0.3) {
                        this.backward = !this.backward;
                    }

                    this.combatTicks = 0;
                }

                if (this.combatTicks > -1) {
                    if (d > (double)(this.squaredRange * 0.75F)) {
                        this.backward = false;
                    } else if (d < (double)(this.squaredRange * 0.25F)) {
                        this.backward = true;
                    }

                    this.actor.getMoveControl().strafeTo(this.backward ? -0.5F : 0.5F, this.movingToLeft ? 0.5F : -0.5F);
                    if (this.actor.getControllingVehicle() instanceof MobEntity mobEntity) {
                        mobEntity.lookAtEntity(livingEntity, 30.0F, 30.0F);
                    }

                    this.actor.lookAtEntity(livingEntity, 30.0F, 30.0F);
                } else {
                    this.actor.getLookControl().lookAt(livingEntity, 30.0F, 30.0F);
                }

                if (this.actor.isUsingItem()) {
                    if (!bl && this.targetSeeingTicker < -60) {
                        this.actor.clearActiveItem();
                    } else if (bl) {
                        int i = this.actor.getItemUseTime();
                        if (i >= 20) {
                            this.actor.clearActiveItem();
                            this.actor.shootAt(livingEntity, BowItem.getPullProgress(i));
                            this.cooldown = this.attackInterval;
                        }
                    }
                } else if (--this.cooldown <= 0 && this.targetSeeingTicker >= -60) {
                    this.actor.setCurrentHand(ProjectileUtil.getHandPossiblyHolding(this.actor, getHeldBow()));
                }
            }
        }
    }
}
