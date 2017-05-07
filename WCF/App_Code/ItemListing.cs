namespace Team4
{
    using System;
    using System.Collections.Generic;
    using System.ComponentModel.DataAnnotations;
    using System.ComponentModel.DataAnnotations.Schema;
    using System.Data.Entity.Spatial;

    [Table("ItemListing")]
    public partial class ItemListing
    {
        [StringLength(50)]
        public string ID { get; set; }

        [StringLength(50)]
        public string ProductName { get; set; }

        [StringLength(150)]
        public string Description { get; set; }

        [Column(TypeName = "money")]
        public decimal? Price { get; set; }

        [StringLength(50)]
        public string Category { get; set; }

        [StringLength(50)]
        public string Status { get; set; }

        public int? Quantity { get; set; }
    }
}
