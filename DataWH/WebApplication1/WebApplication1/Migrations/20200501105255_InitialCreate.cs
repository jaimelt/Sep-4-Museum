using Microsoft.EntityFrameworkCore.Migrations;

namespace WebApplication1.Migrations
{
    public partial class InitialCreate : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.CreateTable(
                name: "Artworks",
                columns: table => new
                {
                    Id = table.Column<string>(type: "TEXT", nullable: false),
                    Name = table.Column<string>(type: "TEXT", nullable: true),
                    description = table.Column<string>(type: "TEXT", nullable: true),
                    image = table.Column<string>(type: "TEXT", nullable: true),
                    type = table.Column<string>(type: "TEXT", nullable: true),
                    author = table.Column<string>(type: "TEXT", nullable: true),
                    location = table.Column<string>(type: "TEXT", nullable: true)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Artworks", x => x.Id);
                });

            migrationBuilder.CreateTable(
                name: "Museum",
                columns: table => new
                {
                    id = table.Column<int>(type: "INTEGER", nullable: false)
                        .Annotation("Sqlite:Autoincrement", true)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Museum", x => x.id);
                });

            migrationBuilder.CreateTable(
                name: "ArtworkMeasurements",
                columns: table => new
                {
                    id = table.Column<int>(type: "INTEGER", nullable: false)
                        .Annotation("Sqlite:Autoincrement", true),
                    minLight = table.Column<int>(type: "INTEGER", nullable: false),
                    minCo2 = table.Column<int>(type: "INTEGER", nullable: false),
                    minTemp = table.Column<int>(type: "INTEGER", nullable: false),
                    maxLight = table.Column<int>(type: "INTEGER", nullable: false),
                    maxCo2 = table.Column<int>(type: "INTEGER", nullable: false),
                    maxTemp = table.Column<int>(type: "INTEGER", nullable: false),
                    ArtworkId = table.Column<string>(type: "TEXT", nullable: true)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_ArtworkMeasurements", x => x.id);
                    table.ForeignKey(
                        name: "FK_ArtworkMeasurements_Artworks_ArtworkId",
                        column: x => x.ArtworkId,
                        principalTable: "Artworks",
                        principalColumn: "Id",
                        onDelete: ReferentialAction.Restrict);
                });

            migrationBuilder.CreateTable(
                name: "Administrators",
                columns: table => new
                {
                    id = table.Column<int>(type: "INTEGER", nullable: false)
                        .Annotation("Sqlite:Autoincrement", true),
                    username = table.Column<string>(type: "TEXT", nullable: false),
                    password = table.Column<string>(type: "TEXT", nullable: false),
                    Museumid = table.Column<int>(type: "INTEGER", nullable: true)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Administrators", x => x.id);
                    table.ForeignKey(
                        name: "FK_Administrators_Museum_Museumid",
                        column: x => x.Museumid,
                        principalTable: "Museum",
                        principalColumn: "id",
                        onDelete: ReferentialAction.Restrict);
                });

            migrationBuilder.CreateTable(
                name: "Rooms",
                columns: table => new
                {
                    locationCode = table.Column<string>(type: "TEXT", nullable: false),
                    description = table.Column<string>(type: "TEXT", nullable: true),
                    currentCapacity = table.Column<int>(type: "INTEGER", nullable: false),
                    totalCapacity = table.Column<int>(type: "INTEGER", nullable: false),
                    Museumid = table.Column<int>(type: "INTEGER", nullable: true)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Rooms", x => x.locationCode);
                    table.ForeignKey(
                        name: "FK_Rooms_Museum_Museumid",
                        column: x => x.Museumid,
                        principalTable: "Museum",
                        principalColumn: "id",
                        onDelete: ReferentialAction.Restrict);
                });

            migrationBuilder.CreateTable(
                name: "RoomMeasurement",
                columns: table => new
                {
                    id = table.Column<int>(type: "INTEGER", nullable: false)
                        .Annotation("Sqlite:Autoincrement", true),
                    light = table.Column<int>(type: "INTEGER", nullable: false),
                    temp = table.Column<int>(type: "INTEGER", nullable: false),
                    humidity = table.Column<int>(type: "INTEGER", nullable: false),
                    Co2 = table.Column<int>(type: "INTEGER", nullable: false),
                    RoomlocationCode = table.Column<string>(type: "TEXT", nullable: true),
                    RoomlocationCode1 = table.Column<string>(type: "TEXT", nullable: true)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_RoomMeasurement", x => x.id);
                    table.ForeignKey(
                        name: "FK_RoomMeasurement_Rooms_RoomlocationCode",
                        column: x => x.RoomlocationCode,
                        principalTable: "Rooms",
                        principalColumn: "locationCode",
                        onDelete: ReferentialAction.Restrict);
                    table.ForeignKey(
                        name: "FK_RoomMeasurement_Rooms_RoomlocationCode1",
                        column: x => x.RoomlocationCode1,
                        principalTable: "Rooms",
                        principalColumn: "locationCode",
                        onDelete: ReferentialAction.Restrict);
                });

            migrationBuilder.CreateIndex(
                name: "IX_Administrators_Museumid",
                table: "Administrators",
                column: "Museumid");

            migrationBuilder.CreateIndex(
                name: "IX_ArtworkMeasurements_ArtworkId",
                table: "ArtworkMeasurements",
                column: "ArtworkId");

            migrationBuilder.CreateIndex(
                name: "IX_RoomMeasurement_RoomlocationCode",
                table: "RoomMeasurement",
                column: "RoomlocationCode");

            migrationBuilder.CreateIndex(
                name: "IX_RoomMeasurement_RoomlocationCode1",
                table: "RoomMeasurement",
                column: "RoomlocationCode1");

            migrationBuilder.CreateIndex(
                name: "IX_Rooms_Museumid",
                table: "Rooms",
                column: "Museumid");
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropTable(
                name: "Administrators");

            migrationBuilder.DropTable(
                name: "ArtworkMeasurements");

            migrationBuilder.DropTable(
                name: "RoomMeasurement");

            migrationBuilder.DropTable(
                name: "Artworks");

            migrationBuilder.DropTable(
                name: "Rooms");

            migrationBuilder.DropTable(
                name: "Museum");
        }
    }
}
