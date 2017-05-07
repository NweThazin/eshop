namespace Team4
{
    using System;
    using System.Data.Entity;
    using System.ComponentModel.DataAnnotations.Schema;
    using System.Linq;

    public partial class Team4Model : DbContext
    {
        public Team4Model()
            : base("name=Team4Model")
        {
        }

        public virtual DbSet<ItemListing> ItemListings { get; set; }

        protected override void OnModelCreating(DbModelBuilder modelBuilder)
        {
            modelBuilder.Entity<ItemListing>()
                .Property(e => e.Price)
                .HasPrecision(19, 4);
        }
    }
}
